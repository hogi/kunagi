// // ----------> GENERATED FILE - DON'T TOUCH! <----------

package scrum.client.i18n;

public class TextBundleViews {

    protected static ilarkesto.core.logging.Log log = ilarkesto.core.logging.Log.get(TextBundleViews.class);

    public String blog() {
        return "<p>The blog can be used to communicate project related information, like release notes or development roadmaps, to the public.</p>\n\n<p>\nEvery project member can create and edit Blog Entries.\nAfter creation, Entries are not published by default, to allow to prepare Entries in advance.\nThe Product Owner can choose to publish Blog Entries. Only published Entries will be taken into account when project data is exported.\n</p>";
    }

    public String calendar() {
        return "<p>Use the calendar to schedule project-related events.</p>\n\n<p>There is a number of Scrum related meetings to be scheduled.</p>\n\nEvery Day at the same time (and the same place) a <strong>Daily Scrum</strong> should be held (and supervised by the Scrum Master). In this status meeting everybody answers three questions:\n<ul>\n\t<li>What did you do yesterday?</li>\n\t<li>What are you going to do today?</li>\n\t<li>Are there any impediments?</li>\n</ul>\n\nAdditionally, at the beginning of a Sprint, the Product Owner invites the Team to do a <strong>Scrum Planning Meeting</strong>.\n\nAt the end of Sprints, <strong>Sprint Review</strong> and <strong>Sprint Retrospective Meetings</strong> are held.";
    }

    public String courtRoom() {
        return "<p>The court room can be used to keep track of project participant\'s rule violations.</p>\n\n<p>\nFor example, you could agree on Team Members having to pay one Euro when they are late to Daily Scrum Meetings.\nEvery time a Team Member is late, the number of violations is then increased. When you reach an sufficient amount of money, you can throw a party and use it to buy cake and free beer.\n</p>";
    }

    public String fileRepository() {
        return "<p>Use the File Repository to upload files that can be conveniently linked within the project.</p>\n\n<p>\nAn uploaded file is assigned an ID <i>fleN</i> (where <i>N</i> is some number).\nWhen you use this ID somewhere within Kunagi (like in entity descriptions or the chat),\na link to the corresponding file is automatically created.\n</p>\n\n<p>\nYou can use the upload feature not only in the File Repository view, but also using\nthe text editor toolbar when editing other entities within the project.\n</p>";
    }

    public String forum() {
        return "<p>The forum is a place to discuss with other project participants.</p>\n\n<p>\nThere are two kinds of subjects visible in the Forum view. Firstly, subjects created\nin the Forum view. Secondly, discussions that are attached to other entities. You can\ndistinguish them by looking at the entity ID. Independent discussions have IDs like\nsbjN (where N is some number), whereas discussions attached to some entity have the same\nID as the corresponding entity (for example sto42 for a discussion for the Story with the\nID sto42).\n</p>";
    }

    public String impediments() {
        return "<p>The Scrum Master uses the Impediment view to keep track of Impediments that encumber the project.</p>\n\n<p>\nWhen impediments come up (e. g. are given by a Team Member during a Daily Scrum Meeting),\nthe Scrum Master creates an Impediment to document it\'s existence. He then works to\nremove Impediments that obstruct the Team\'s work. As soon as a solution is found, he\n<i>closes</i> the Impediment and documents the solution, so then Team Members can check\nback to see if and how an Impediment was solved.\n</p>";
    }

    public String issues() {
        return "<p>The Issue view is a place to keep track of feedback, bugs and ideas for the project.</p>\n\n<p>\nIt is divided into four parts, namely <strong>Issue Inbox</strong>, <strong>Bugs</strong>,\n<strong>Ideas</strong> and <strong>Closed Issues</strong>. When an Issue is submitted, it\nis created in the Inbox.\n</p>\n\n<p>\nThe Product Owner reviews all Issues in the Inbox and decides how to handle them. He can\nchoose to <i>suspend</i> them if he wants to postpone a decision. Otherwise he selects to\nconvert the Issue to a <strong>Bug</strong> or an <strong>Idea</strong>.\n</p>\n\n<p>\n<strong>Bugs</strong> are the Team\'s domain and are usually flaws of part work results that\nhave been discovered only now. When a Sprint Planning Meeting is held and the Team decides\non how much work they can handle during the next Sprint, they should also take work into\naccount that has to be invested into bug solving. In case a bug\'s scope is too big or\notherwise cloudy, it might be a better Idea to create a Story from it.\n</p>\n\n<p>\n<strong>Ideas</strong> on the other hand are to be handled by the Product Owner. An idea is\ncreated feedback is accepted, but a Story for the Product Backlog is yet to be created.\n</p>\n\n<p>\n<strong>Closed Issues</strong> are all Issues that have been sufficiently addressed.\nSolutions of issues include resolution of the issue, identifying it as a dublicate\nof another issue, creating stories from them and refusing to address them.\n</p>";
    }

    public String journal() {
        return "<p>The Project Journal chronologically records all important events in the projects.</p>\n\n<p>\nWhen users make important changes to project data, the action is recorded and placed in\nthe Project Journal. This is used to show the latest events in the Dashboard. Older actions\ncan be looked up in the Project Journal view.\n</p>\n\n<p>\nChanges done to Entities are not recorded here. Instead, they can be viewed by selecting\n<i>Show Change Log</i> from the Entities action menu.\n</p>";
    }

    public String nextSprint() {
        return "<p>The Next Sprint view is used to prepare the next Sprint in advance.</p>\n\n<p>\nThe Product Owner can select start date and Sprint duration and should give the Sprint a\nname based on the Sprint Goal. After the Sprint Review Meeting of the current Sprint is,\nthe Product Owner selects <i>Switch to this Sprint</i> in order to finish and file the\ncurrent Sprint replace the current Sprint Backlog by the new one. All unfinished Stories\nare then moved back to the Product Backlog and all unfinished Tasks are deleted.\n</p>";
    }

    public String personalPreferences() {
        return "@personalPreferences";
    }

    public String productBacklog() {
        return "<p>The Product Backlog is a priotized list of desired product features.</p>\n\n<p>\nThe Product Owner is reponsible for creating the Product Backlog and keeping it up to date.\nIt contains Stories that describe desired product features. Furthermore, the Stories are\npriotized: the more important the feature the higher in the Product Backlog it is placed.\n</p>\n\n<p>\nDuring Sprint Planning Meetings, the Team picks Stories from the top (and only the top)\nof the Product Backlog and commits to implement them during the upcoming Sprint. By placing\nthe features he wants to see implemented next on top of the Product Backlog, the Product\nOwnercan decide the direction of the project.\n</p>\n\n<p>\nBefore the Team can start working on Stories, Team Members have to estimate them relative\nto each other (i. e. a Story estimated with 2 points presumably takes twice as much effort\nas a 1 point Story). In order to be able to do so, the Product Owner needs to provide a\nsufficient description and explanation of the Story. Once stories are pulled to a Sprint,\nthey may not be changed anymore. Therefore, the Product Owner should alway be present for\nestimation and explain the Stories to the team (and adjust the Entity if neccessary).\n</p>\n\n<p>\nAs a rule of thumb, there should be enough detailed and estimated Stories for the next\ntwo to three Sprints. Putting too much detail into low priotized Stories should be avoided,\nas details are likely to change in the future.\n</p>\n\n<p>\nThe velocity history and avarage velocity values show how many story points the team was\nable to complete in past Sprints and on avarage. By adjusting the anticipated velocity\nvalue, the Product Owner and other project members can project an approximation of what\nwill be completed in the following Sprints. It also helps the Team to decide, how many\nStories they can commit to during the Sprint Planning Meeting.\n</p>";
    }

    public String projectAdministration() {
        return "@projectAdministration";
    }

    public String qualities() {
        return "<p>Qualities are Stories that are non-functional (more generally) requirements that range multiple Stories.</p>\n\n<p>\nSome requirements put general demands on the implementation of the project. In that case,\nthose specific requirements are likely to appear in multiple Stories. In order to avoid\nrepetition, Qualities can be created and linked to Stories in the Product Backlog.\n</p>\n\n<p>\nA popular example of such requirements are non-functional requirements in software. When\nlisting functional requirements in a table, non-functional requirements are best imagined\non another axis, affecting one or more of the functional requirements.\n</p>\n\n<p>\nFor example, with Stories listed on the left and Qualities on top and X indicating that\nStoriy and Quality are linked:\n</p>\n\n<table>\n\t<tr>\n\t\t<td>&nbsp;</td>\n\t\t<td>User Documentation</td>\n\t\t<td>Thread Safety</td>\n\t</tr>\n\t<tr>\n\t\t<td>Create User Framework</td>\n\t\t<td>&nbsp;</td>\n\t\t<td>X</td>\n\t</tr>\n\t<tr>\n\t\t<td>Create Administration View</td>\n\t\t<td>X</td>\n\t\t<td>&nbsp;</td>\n\t</tr>\n\t<tr>\n\t\t<td>Create Login View</td>\n\t\t<td>X</td>\n\t\t<td>&nbsp;</td>\n\t</tr>\n</table>\n\n\n\n";
    }

    public String releases() {
        return "<p>Releases can be used to keep track of product versions and their specifics.</p>\n\n<p>\nThe Product Owner can create Releases that represent product versions that are released\nto the public. There are two types of Releases: <strong>Releases</strong> and \n<strong>Bugfix Releases</strong>. The former represent a major product version while\nthe latter are a minor change to the associated Release (that might be necessary if bugs\nare found that have to be fixed quickly).\n</p>\n\n<p>\nA Release usually spans a number of Sprints and includes all Stories completed during\nthose Sprints. Additionally, known Bugs can be associated with both types of releases\nas known (but not fixed) and fixed.\n</p>";
    }

    public String risks() {
        return "<p>Risks are anticipated events that can have an adverse impact on the project.</p>\n\n<p>\nProject Members should do risk management to avoid project failure due to unexpected\noccurance of problems. By anticipating and evaluating possible problems, risk management\nallows for the project to continue smoothly, even when problems occur.\n</p>\n\n<p>\nRisks are evaluated by estimating probability and impact. The probability of a Risk is the\nlikeliness of the risk becoming a problem, while the impact is the damage to the project\nthat might be induced by the problem. By developing probability and impact mitigation\nplans the Team can reduce probability and impact, thus making a favourable project outcome\nmore likely.\n</p>\n\n<p>\nRisks are priotized automatically based on probability and impact values according to\nthe following table:\n</p>\n\n<table>\n\t<tr>\n\t\t<td>&nbsp;</td>\n\t\t<td>very unlikely</td>\n\t\t<td>unlikely</td>\n\t\t<td>possible</td>\n\t\t<td>likely</td>\n\t\t<td>very likely</td>\n\t</tr>\n\t<tr>\n\t\t<td>extreme</td>\n\t\t<td>Severe</td>\n\t\t<td>Severe</td>\n\t\t<td>High</td>\n\t\t<td>Significant</td>\n\t\t<td>Moderate</td>\n\t</tr>\n\t<tr>\n\t\t<td>substantial</td>\n\t\t<td>Very High</td>\n\t\t<td>High</td>\n\t\t<td>Significant</td>\n\t\t<td>Moderate</td>\n\t\t<td>Moderate</td>\n\t</tr>\n\t<tr>\n\t\t<td>medium</td>\n\t\t<td>High</td>\n\t\t<td>Significant</td>\n\t\t<td>Moderate</td>\n\t\t<td>Moderate</td>\n\t\t<td>Moderate</td>\n\t</tr>\n\t<tr>\n\t\t<td>minor</td>\n\t\t<td>Significant</td>\n\t\t<td>Moderate</td>\n\t\t<td>Moderate</td>\n\t\t<td>Low</td>\n\t\t<td>Very Low</td>\n\t</tr>\n\t<tr>\n\t\t<td>negligible</td>\n\t\t<td>Moderate</td>\n\t\t<td>Moderate</td>\n\t\t<td>Low</td>\n\t\t<td>Very Low</td>\n\t\t<td>Very Low</td>\n\t</tr>\n</table>\n\n<p>Risk should be constantly monitored and and reviewed at least once a month.</p>";
    }

    public String sprintBacklog() {
        return "<p>The Sprint Backlog is used by the Team to organize their current work.</p>\n\n<p>\nIt consists of Stories selected from the Product Backlog during the preceeding Sprint\nPlanning Meeting. For every Story, the Team creates Tasks that - once completed - complete\nthe corresponding Story. Only completed Stories count towards the Team\'s success, therefore\nwork should be concentrated rather than spread across Stories. Figuratively speaking,\ncompleting half of each Story means a success rate of 0% while completing half of the\nStories means a success rate of 50%.\n</p>\n\n<p>\nOnce there are no Tasks left to do, the Product Owner can review and <i>accept</i> Stories\nas solved during the Sprint Review. When the Sprint ends, only Stories that have been\naccepted as solved by the Product Owner count towards the Team\'s velocity. Stories that\nare not accepted are moved back to the Product Backlog.\n</p>";
    }

    public String sprintHistory() {
        return "@sprintHistory";
    }

    public String whiteboard() {
        return "<p>The Whiteboard is a visual representation of the current Sprint\'s progress.</p>\n\n<p>\nIt displays the current Sprint Backlog, arranging Tasks to indicate the Sprint\'s progress.\nTasks that are yet untouched are on the left, Tasks that are currently being worked on can\nbe found in the middle and finished Tasks on the right. Like moving sticky notes on a real\nwhiteboard, Team members can move Tasks around, setting them claimed or completed as they\ndo so.\n</p>\n\n<p>\nThe Sprint approaches completion as Tasks move from left to right.\n</p>";
    }

    public String wiki() {
        return "@wiki";
    }

}

