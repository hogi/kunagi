package scrum.server.estimation;

import ilarkesto.fp.Predicate;
import ilarkesto.logging.Logger;

import java.util.HashSet;
import java.util.Set;

import scrum.server.admin.User;
import scrum.server.project.Requirement;

public class RequirementEstimationVoteDao extends GRequirementEstimationVoteDao {

	private static final Logger LOG = Logger.get(RequirementEstimationVoteDao.class);

	public RequirementEstimationVote getRequirementEstimationVoteByUser(final Requirement requirement, final User user) {
		return getEntity(new Predicate<RequirementEstimationVote>() {

			public boolean test(RequirementEstimationVote vote) {
				return vote.isRequirement(requirement) && vote.isUser(user);
			}
		});
	}

	private Set<RequirementEstimationVote> getRequirementEstimationVotesByUser(final Requirement requirement,
			final User user) {
		return getEntities(new Predicate<RequirementEstimationVote>() {

			public boolean test(RequirementEstimationVote vote) {
				return vote.isRequirement(requirement) && vote.isUser(user);
			}
		});
	}

	public RequirementEstimationVote postVote(Requirement requirement, User user) {
		RequirementEstimationVote vote = newEntityInstance();
		vote.setRequirement(requirement);
		vote.setUser(user);
		saveEntity(vote);
		return vote;
	}

	@Override
	public void ensureIntegrity() {
		super.ensureIntegrity();

		Set<Requirement> requirements = new HashSet<Requirement>();
		for (RequirementEstimationVote vote : getEntities()) {
			requirements.add(vote.getRequirement());
		}
		for (Requirement requirement : requirements) {
			Set<User> users = requirement.getProject().getParticipants();
			for (User user : users) {
				Set<RequirementEstimationVote> votes = getRequirementEstimationVotesByUser(requirement, user);
				if (votes.size() > 1) {
					LOG.warn("Multiple estimation votes. Deleting all.");
					for (RequirementEstimationVote vote : votes) {
						deleteEntity(vote);
					}
				}
			}
		}
	}

}