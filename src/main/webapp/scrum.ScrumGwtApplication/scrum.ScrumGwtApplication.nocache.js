function scrum_ScrumGwtApplication(){
  var $intern_0 = '', $intern_27 = '" for "gwt:onLoadErrorFn"', $intern_25 = '" for "gwt:onPropertyErrorFn"', $intern_10 = '"><\/script>', $intern_12 = '#', $intern_14 = '/', $intern_47 = '20878C99AA1E5C6B066E82E6BD31320F.cache.html', $intern_49 = '84556E84F20988B076E0BDEEEBDF928B.cache.html', $intern_46 = '91E441A45A926BA7F3A90AA922199C00.cache.html', $intern_50 = '9E09FBE25C6C052660134CD4CFA1698A.cache.html', $intern_60 = '<script defer="defer">scrum_ScrumGwtApplication.onInjectionDone(\'scrum.ScrumGwtApplication\')<\/script>', $intern_9 = '<script id="', $intern_22 = '=', $intern_13 = '?', $intern_24 = 'Bad handler "', $intern_45 = 'D1A79455FEDBE2D684CAF6AF925AE4CA.cache.html', $intern_48 = 'DD653B988057BC5AD0EF48F8A11CC78D.cache.html', $intern_59 = 'DOMContentLoaded', $intern_11 = 'SCRIPT', $intern_8 = '__gwt_marker_scrum.ScrumGwtApplication', $intern_15 = 'base', $intern_4 = 'begin', $intern_3 = 'bootstrap', $intern_17 = 'clear.cache.gif', $intern_21 = 'content', $intern_7 = 'end', $intern_39 = 'gecko', $intern_40 = 'gecko1_8', $intern_51 = 'gwt-dnd.css', $intern_5 = 'gwt.hybrid', $intern_57 = 'gwt/standard/standard.css', $intern_26 = 'gwt:onLoadErrorFn', $intern_23 = 'gwt:onPropertyErrorFn', $intern_20 = 'gwt:property', $intern_56 = 'head', $intern_43 = 'hosted.html?scrum_ScrumGwtApplication', $intern_55 = 'href', $intern_38 = 'ie6', $intern_37 = 'ie8', $intern_28 = 'iframe', $intern_16 = 'img', $intern_29 = "javascript:''", $intern_52 = 'link', $intern_42 = 'loadExternalRefs', $intern_18 = 'meta', $intern_31 = 'moduleRequested', $intern_6 = 'moduleStartup', $intern_36 = 'msie', $intern_19 = 'name', $intern_33 = 'opera', $intern_30 = 'position:absolute;width:0;height:0;border:none', $intern_53 = 'rel', $intern_35 = 'safari', $intern_58 = 'screen.css', $intern_1 = 'scrum.ScrumGwtApplication', $intern_44 = 'selectingPermutation', $intern_2 = 'startup', $intern_54 = 'stylesheet', $intern_41 = 'unknown', $intern_32 = 'user.agent', $intern_34 = 'webkit';
  var $wnd = window, $doc = document, $stats = $wnd.__gwtStatsEvent?function(a){
    return $wnd.__gwtStatsEvent(a);
  }
  :null, scriptsDone, loadDone, bodyDone, base = $intern_0, metaProps = {}, values = [], providers = [], answers = [], onLoadErrorFunc, propertyErrorFunc;
  $stats && $stats({moduleName:$intern_1, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date()).getTime(), type:$intern_4});
  if (!$wnd.__gwt_stylesLoaded) {
    $wnd.__gwt_stylesLoaded = {};
  }
  if (!$wnd.__gwt_scriptsLoaded) {
    $wnd.__gwt_scriptsLoaded = {};
  }
  function isHostedMode(){
    var result = false;
    try {
      result = $wnd.external && ($wnd.external.gwtOnLoad && $wnd.location.search.indexOf($intern_5) == -1);
    }
     catch (e) {
    }
    isHostedMode = function(){
      return result;
    }
    ;
    return result;
  }

  function maybeStartModule(){
    if (scriptsDone && loadDone) {
      var iframe = $doc.getElementById($intern_1);
      var frameWnd = iframe.contentWindow;
      if (isHostedMode()) {
        frameWnd.__gwt_getProperty = function(name){
          return computePropValue(name);
        }
        ;
      }
      scrum_ScrumGwtApplication = null;
      frameWnd.gwtOnLoad(onLoadErrorFunc, $intern_1, base);
      $stats && $stats({moduleName:$intern_1, subSystem:$intern_2, evtGroup:$intern_6, millis:(new Date()).getTime(), type:$intern_7});
    }
  }

  function computeScriptBase(){
    var thisScript, markerId = $intern_8, markerScript;
    $doc.write($intern_9 + markerId + $intern_10);
    markerScript = $doc.getElementById(markerId);
    thisScript = markerScript && markerScript.previousSibling;
    while (thisScript && thisScript.tagName != $intern_11) {
      thisScript = thisScript.previousSibling;
    }
    function getDirectoryOfFile(path){
      var hashIndex = path.lastIndexOf($intern_12);
      if (hashIndex == -1) {
        hashIndex = path.length;
      }
      var queryIndex = path.indexOf($intern_13);
      if (queryIndex == -1) {
        queryIndex = path.length;
      }
      var slashIndex = path.lastIndexOf($intern_14, Math.min(queryIndex, hashIndex));
      return slashIndex >= 0?path.substring(0, slashIndex + 1):$intern_0;
    }

    ;
    if (thisScript && thisScript.src) {
      base = getDirectoryOfFile(thisScript.src);
    }
    if (base == $intern_0) {
      var baseElements = $doc.getElementsByTagName($intern_15);
      if (baseElements.length > 0) {
        base = baseElements[baseElements.length - 1].href;
      }
       else {
        base = getDirectoryOfFile($doc.location.href);
      }
    }
     else if (base.match(/^\w+:\/\//)) {
    }
     else {
      var img = $doc.createElement($intern_16);
      img.src = base + $intern_17;
      base = getDirectoryOfFile(img.src);
    }
    if (markerScript) {
      markerScript.parentNode.removeChild(markerScript);
    }
  }

  function processMetas(){
    var metas = document.getElementsByTagName($intern_18);
    for (var i = 0, n = metas.length; i < n; ++i) {
      var meta = metas[i], name = meta.getAttribute($intern_19), content;
      if (name) {
        if (name == $intern_20) {
          content = meta.getAttribute($intern_21);
          if (content) {
            var value, eq = content.indexOf($intern_22);
            if (eq >= 0) {
              name = content.substring(0, eq);
              value = content.substring(eq + 1);
            }
             else {
              name = content;
              value = $intern_0;
            }
            metaProps[name] = value;
          }
        }
         else if (name == $intern_23) {
          content = meta.getAttribute($intern_21);
          if (content) {
            try {
              propertyErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_24 + content + $intern_25);
            }
          }
        }
         else if (name == $intern_26) {
          content = meta.getAttribute($intern_21);
          if (content) {
            try {
              onLoadErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_24 + content + $intern_27);
            }
          }
        }
      }
    }
  }

  function unflattenKeylistIntoAnswers(propValArray, value){
    var answer = answers;
    for (var i = 0, n = propValArray.length - 1; i < n; ++i) {
      answer = answer[propValArray[i]] || (answer[propValArray[i]] = []);
    }
    answer[propValArray[n]] = value;
  }

  function computePropValue(propName){
    var value = providers[propName](), allowedValuesMap = values[propName];
    if (value in allowedValuesMap) {
      return value;
    }
    var allowedValuesList = [];
    for (var k in allowedValuesMap) {
      allowedValuesList[allowedValuesMap[k]] = k;
    }
    if (propertyErrorFunc) {
      propertyErrorFunc(propName, allowedValuesList, value);
    }
    throw null;
  }

  var frameInjected;
  function maybeInjectFrame(){
    if (!frameInjected) {
      frameInjected = true;
      var iframe = $doc.createElement($intern_28);
      iframe.src = $intern_29;
      iframe.id = $intern_1;
      iframe.style.cssText = $intern_30;
      iframe.tabIndex = -1;
      $doc.body.appendChild(iframe);
      $stats && $stats({moduleName:$intern_1, subSystem:$intern_2, evtGroup:$intern_6, millis:(new Date()).getTime(), type:$intern_31});
      iframe.contentWindow.location.replace(base + strongName);
    }
  }

  providers[$intern_32] = function(){
    var ua = navigator.userAgent.toLowerCase();
    var makeVersion = function(result){
      return parseInt(result[1]) * 1000 + parseInt(result[2]);
    }
    ;
    if (ua.indexOf($intern_33) != -1) {
      return $intern_33;
    }
     else if (ua.indexOf($intern_34) != -1) {
      return $intern_35;
    }
     else if (ua.indexOf($intern_36) != -1) {
      if (document.documentMode >= 8) {
        return $intern_37;
      }
       else {
        var result = /msie ([0-9]+)\.([0-9]+)/.exec(ua);
        if (result && result.length == 3) {
          var v = makeVersion(result);
          if (v >= 6000) {
            return $intern_38;
          }
        }
      }
    }
     else if (ua.indexOf($intern_39) != -1) {
      var result = /rv:([0-9]+)\.([0-9]+)/.exec(ua);
      if (result && result.length == 3) {
        if (makeVersion(result) >= 1008)
          return $intern_40;
      }
      return $intern_39;
    }
    return $intern_41;
  }
  ;
  values[$intern_32] = {gecko:0, gecko1_8:1, ie6:2, ie8:3, opera:4, safari:5};
  scrum_ScrumGwtApplication.onScriptLoad = function(){
    if (frameInjected) {
      loadDone = true;
      maybeStartModule();
    }
  }
  ;
  scrum_ScrumGwtApplication.onInjectionDone = function(){
    scriptsDone = true;
    $stats && $stats({moduleName:$intern_1, subSystem:$intern_2, evtGroup:$intern_42, millis:(new Date()).getTime(), type:$intern_7});
    maybeStartModule();
  }
  ;
  computeScriptBase();
  var strongName;
  if (isHostedMode()) {
    if ($wnd.external.initModule && $wnd.external.initModule($intern_1)) {
      $wnd.location.reload();
      return;
    }
    strongName = $intern_43;
  }
  processMetas();
  $stats && $stats({moduleName:$intern_1, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date()).getTime(), type:$intern_44});
  if (!strongName) {
    try {
      unflattenKeylistIntoAnswers([$intern_37], $intern_45);
      unflattenKeylistIntoAnswers([$intern_38], $intern_46);
      unflattenKeylistIntoAnswers([$intern_35], $intern_47);
      unflattenKeylistIntoAnswers([$intern_33], $intern_48);
      unflattenKeylistIntoAnswers([$intern_40], $intern_49);
      unflattenKeylistIntoAnswers([$intern_39], $intern_50);
      strongName = answers[computePropValue($intern_32)];
    }
     catch (e) {
      return;
    }
  }
  var onBodyDoneTimerId;
  function onBodyDone(){
    if (!bodyDone) {
      bodyDone = true;
      if (!__gwt_stylesLoaded[$intern_51]) {
        var l = $doc.createElement($intern_52);
        __gwt_stylesLoaded[$intern_51] = l;
        l.setAttribute($intern_53, $intern_54);
        l.setAttribute($intern_55, base + $intern_51);
        $doc.getElementsByTagName($intern_56)[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded[$intern_57]) {
        var l = $doc.createElement($intern_52);
        __gwt_stylesLoaded[$intern_57] = l;
        l.setAttribute($intern_53, $intern_54);
        l.setAttribute($intern_55, base + $intern_57);
        $doc.getElementsByTagName($intern_56)[0].appendChild(l);
      }
      if (!__gwt_stylesLoaded[$intern_58]) {
        var l = $doc.createElement($intern_52);
        __gwt_stylesLoaded[$intern_58] = l;
        l.setAttribute($intern_53, $intern_54);
        l.setAttribute($intern_55, base + $intern_58);
        $doc.getElementsByTagName($intern_56)[0].appendChild(l);
      }
      maybeStartModule();
      if ($doc.removeEventListener) {
        $doc.removeEventListener($intern_59, onBodyDone, false);
      }
      if (onBodyDoneTimerId) {
        clearInterval(onBodyDoneTimerId);
      }
    }
  }

  if ($doc.addEventListener) {
    $doc.addEventListener($intern_59, function(){
      maybeInjectFrame();
      onBodyDone();
    }
    , false);
  }
  var onBodyDoneTimerId = setInterval(function(){
    if (/loaded|complete/.test($doc.readyState)) {
      maybeInjectFrame();
      onBodyDone();
    }
  }
  , 50);
  $stats && $stats({moduleName:$intern_1, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date()).getTime(), type:$intern_7});
  $stats && $stats({moduleName:$intern_1, subSystem:$intern_2, evtGroup:$intern_42, millis:(new Date()).getTime(), type:$intern_4});
  $doc.write($intern_60);
}

scrum_ScrumGwtApplication();
