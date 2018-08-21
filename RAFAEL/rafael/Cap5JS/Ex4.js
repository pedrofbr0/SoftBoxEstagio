function characterScript(code) {
  for (let script of SCRIPTS) {
    if (script.ranges.some(([from, to]) => {
      return code >= from && code < to;
    })) {
      return script;
    }
  }
  return null;
}

function textScripts(text) {
  let scripts = countBy(text, char => {
    let script = characterScript(char.codePointAt(0));
    if(script==null){
    	return "none";
    }
    else{
    	return script.direction;
    }
  }).filter(({name}) => name != "none");
  
  return scripts;

  let total = scripts.reduce((n, {count}) => n + count, 0);
  if (total == 0) return "No scripts found";
  
  
}

function dominantDirection(text) {
  	scripts = textScripts(text);
  	let max = 0;
 	let elementoMaximo = null;
  
  	for (let element of scripts){
      	if(element.count>max){
          max = element.count;
          elementoMaximo = element;
        }
    }
 	
  	return elementoMaximo.name;
}

console.log(dominantDirection("Hello!"));
// → ltr
console.log(dominantDirection("Hey, مساء الخير"));
// → rtl
