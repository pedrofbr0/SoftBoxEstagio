function loop(value, test, update, action){
  for(var i = value; test(i); i = update(i)){
   		action(i); 
  }
}

loop(3, n => n > 0, n => n - 1, console.log);
// → 3
// → 2
// → 1
