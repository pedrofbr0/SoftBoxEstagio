function reverseArray(array){
	var reverso = [];
  for(let element of array){
  	reverso.unshift(element);  
  }
  return reverso;
}

function reverseArrayInPlace(array){
  for(var i = 0; i < Math.floor(array.length/2); i++){
  	var aux = array[i];
    array[i] = array[array.length-i-1];
    array[array.length-i-1] = aux;
  }
}

console.log(reverseArray(["A", "B", "C"]));
// → ["C", "B", "A"];
let arrayValue = [1, 2, 3, 4, 5];
reverseArrayInPlace(arrayValue);
console.log(arrayValue);
// → [5, 4, 3, 2, 1]
