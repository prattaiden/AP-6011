
window.onload = function() {
  document.writeln("<h1>hello world</h1>");
//this will go to the document and webpage
}
console.log("hello world");
//console.log will just print it to the console



myArray = [4, 5.6, "aiden", false];

console.log(myArray);

myArray[0] = 20;

console.log(myArray);
//when printing the array initially, it prints the updated location to 0
//but now on the second print, it is updated the 0th position only in the second console log

function f (a,b){
  console.log(a + b);
}
f(100,10);
f("test", "best");


let myFunction = function(a,b){
  console.log(a + b);
}
myFunction("string", "bing");
myFunction(5.5, 5);
myFunction(100, 200);

//I prefer the first method of just "function f"
//it is simpler to read
//in the function f, using ints mathematically adds them together
//for the "myFunction" it concatinates my two strings
//it also is able to add a decimal number and a regular integer number mathemetically
//
