
function buildTable(){

  let box = '';

  for (let i  = 1; i <= 10; i++){
      box += '<tr>';

      for (let j = 1; j <= 10; j++){
        box += '<td>' + (i*j) +  '</td>';

      }
      box += '</tr>';
  }


  document.getElementById("multiTable").innerHTML = box;
}

//calling function to build the table
document.onload = buildTable();
let td1 = document.getElementsByTagName("td");
for(let td of td1){
  td.addEventListener("mouseover", function (){td.classList.add("light")})
  td.addEventListener("mouseout", function (){td.classList.remove("light")})

  td.addEventListener("click", function() {td.classList.add("light2")})
  // if(td.click()) {
  //   td.addEventListener("click", function () {
  //     td.classList.remove("light2")
  //   })
}



let i = 0;
function changeBackground(){

  let doc = document.getElementById("multiTable");
  let color = ["rgb(200, 200, 0)", "rgb(100, 0, 100)", "rgb(0, 150, 0)"];
  doc.style.backgroundColor = color[i];
  i = (i + 1) % color.length;

}


//document.getElementById("colorBTN")

window.setInterval(changeBackground, 200)








