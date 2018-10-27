//"use strict"; //ensures declaration of all variables

//functions are standalone but methods are attached to an object, fcn itself is an Object

f3(); //example of hoisting

function clickButton() {
     let textValue = document.getElementById("textValue").value;
     console.log(textValue);
     document.getElementById("before").innerHTML = textValue;
}
//javascript has something called strict mode, opposite of dynamically typed

var x = 10;
y = 20, z = 30;
console.log(x, y, z);
y = "hello world";
console.log(x, y, z);

//bad practice
//alert("hello world");
//document.write("hello world");

//let, var, const - scopes
//local, global, also local, block scoped just like variables declared with let
//values of const varaibles can't be chagned or reassigned or redeclared


var a = 10;
let b = 20;

if (a == 10) {
     var variableDeclaredwithVar = "checking for blocked scope using var";
}
console.log("value of varaible declared with var" + variableDeclaredwithVar);
if (b == 20) {
     let variableDeclaredwithLet = "checking for blocked scope using let";
}
//console.log("value of variable decalred with let" + variableDeclaredwithLet);

//what is so special about functions in javascript?
     //functions are objects
     //functions can be anonymous
     //functions can be self invoking
     //functions can be hoisted
     //functions have closures
     //functions can be called back
     //fucntions can be assigned to a variable
     //fucntions are different form methods

var f1 = function() {
     return true;
}
console.log(f1);    //prints function
console.log(f1());  //invokes the function
console.log(typeof(f1)); //checks type, function

var c = function() {
     return false;
};

(function(){
     console.log("is this invoked without calling? really, how?");
}());

//example for self invoking function that's assigned to a variable
let d = (function() {
     console.log("self invoking and assigned to a variable")
}());

//example for self invoking, anonymous and assigned to a variables
let e = (function(){
     console.log("self invoking, anonymous and assigned to a variable");
}());

function f3(){
     console.log("what is hoisting?");
}

//closures
let f = 0;
function add(){
     f += 1;
     console.log(f);
}
add();
add();
add();

//Redeclaring a var variable with let, in the same scope, or in the same scope is not allowed
//Redeclaring a let variable with let, in the same scope, or in the same scope is not allowed
//Redeclaring a let variable with var, in the same scope, or in the same scope is not allowed
//Redeclaring a variable with let, in another same scope, or in another block is allowed

var add7 = (function() {
     var g = 0;
     return function () {
          g += 1;
          return g;
     };
}());

console.log(add7());
console.log(add7());
console.log(add7());

// var add8 = (function() {
//      var g = 0;
//      function f8 () {
//           g += 1;
//           return g;
//      }
//      f8();
// });
//
// console.log(add8);
// console.log(add8);
// console.log(add8);

//create an object using literal
let o1 = {name:"WATMAN", nickname:"WAT", age: "WAT"};

//is javascript pass by value or reference?
//pass by value for arguments
//pass by reference for objects
let o2 = o1;

for (let x in o1){
     console.log(x + " - " + o1[x]);
}

o1.specialMove = "confusion";
o1.superPower = "WAT POWER";
o1.occupation = "does WAT?";

for (let x in o1){
     console.log(x + " - " + o1[x]);
}

//create an object using new keyword
let o3 = new Object();
o3.name = "Carl Lucas";
o3.hero = "Luke Cage";
o3.occupation = "King of Harlem";
//o3.gift = "Punch holes in wall.";

o3.gift = function(){
     return "bullet proof";
}

//create object using constructor
function o4(name, hero, occupation){
     this.name = name;
     this.hero = hero;
     this.occupation = occupation;

     //this is a function but also a method since it is a function that belongs to a class
     this.gift = function(){
          return "bulletproof";
     }
}

//json vs javascript object

let jj = {name: "Jessica Jones", gift: "Jumps super high"};
console.log(jj);
let j2 = JSON.stringify(jj);
console.log(j2); //JS Object to json
let j3 = JSON.parse(j2);
console.log(j3);
