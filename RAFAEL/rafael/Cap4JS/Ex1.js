function deepEqual(a,b){
  	let flag = true;
  
  	if(a===b){
      return true;
    }else{
      	for (let element of Object.keys(a)){
         	if(!Object.keys(b).includes(element)){
             	return false; 
            }
        }
         for(var element in a){
    		if(!(deepEqual(a[element],b[element]))){
              return false;
            }
         }
      	return true;
    }
  	return false;
}

let obj = {here: {is: "an"}, object: 2};
console.log(deepEqual(obj, obj));
// → true
console.log(deepEqual(obj, {here: 1, object: 2}));
// → false
console.log(deepEqual(obj, {here: {is: "an"}, object: 2}));
// → true
