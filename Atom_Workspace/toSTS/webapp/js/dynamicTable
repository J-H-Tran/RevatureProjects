/*
how to find elements

document.getElementById(id)
document.getElementsByTagName(name)
document.Array.from(names)
document.Array.from() - css

how to change / modify elements

element.innerHTML = "new html content";
element.attribute = "new value";
element.setAttribute(attribute,value);
element.style.property = new style;

how to add and delete element?

document.createElement(element)
document.appendChild(element)
document.removeChild(element)
document.write(text)

how to navigate between nodes using JS?
parentNode
childNodes[nodeNumber]
firstChild
lastChild
nextSibling
previousSibling
*/

//how to print all the properties and methods of an object?
// window.onload = function(){
//      var x = document.getElementById("myForm");
//      for (let y in x){
//           console.log(y);
//      }
// }
window.onload = function(){
     document.getElementById("submitBtn").addEventListener("click", addRows);
     document.getElementById("clearBtn").addEventListener("click", clearRows);
}

function addRows(){
     let id = document.getElementById("heroid").value;
     let name = document.getElementById("heroname").value;
     let age = document.getElementById("heroage").value;

     console.log(id + "" + name + "" + age + "");

     if(id && name && age) {
          if (id.trim != "" && name.trim != "" && age.trim != "") {
               let row = document.createElement("tr");
               let col_id = document.createElement("td");
               let col_name = document.createElement("td");
               let col_age = document.createElement("td");

               col_id.textContent = id;
               col_name.textContent = name;
               col_age.textContent = age;

               row.appendChild(col_id);
               row.appendChild(col_name);
               row.appendChild(col_age);

               document.getElementById("myTable").appendChild(row);
          }
     }else{
          
     }
}

function clearRows(){
     let id = document.getElementById("").value;
     let name = document.getElementById("").value;
     let age = document.getElementById("").value;
}
