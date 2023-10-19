// Function to find the index of the minimum element in the array
function findMinLocation(arr, startIndex, compareFunction) {
  //setting the start index as the min so it is the initial compare
  let minIndex = startIndex;

  //starting from startIndex (i in selectionSort) find the min within the "unsorted" portion of the array
  //for loop through the length of the array
  for (let i = startIndex; i < arr.length; i++) {
    // using on of the helper compare functions and its return value
    //if arr[i] < minIndex
    if (compareFunction(arr[i], arr[minIndex])) {
      //updates the minIndex to the current index i, arr[i] is the new minimum element found
      minIndex = i;
    }
  }
  //returning the minimum index
  return minIndex;
}

// Selection Sort Function
function selectionSort(arr, compareFunction) {
  //for loop through the array
  for (let i = 0; i < arr.length; i++) {
    //minindex constant that equals the result of the findminlocation function
    const minIndex = findMinLocation(arr, i, compareFunction);
    //if minIndex does not equal what is at pos i, swap them
    if (minIndex !== i) {
      // Swap the elements
      [arr[i], arr[minIndex]] = [arr[minIndex], arr[i]];
    }
  }
}

// Compare function for sorting integers in ascending order
function compareInt(a, b) {
  return a < b;
}
//sorting strings from smallest in length to biggest in length
function compareString(a, b) {
  return a.length < b.length;
}

//array of int numbers
const intArray = [500, 30, 73, 11, 98421, 2];
selectionSort(intArray, compareInt);
console.log( intArray);

//array of float numbers
const floatArray = [5.2, 44.1, 44.2, 1.02, 99.2, 444.2];
selectionSort(floatArray, compareInt);
console.log(floatArray);

//array of strings
const strArray = ["cervelo", "zortingtestlongstring", "a", "skeleton", "dog"];
selectionSort(strArray, compareString);
console.log( strArray);

 const personArray = [
    { first: "Ben", last: "Zoo"},
    { first: "Nick", last: "Chaney"},
   {first: "Aiden", last: "Grant"},
   { first: "Aiden", last: "Pratt"},
   {first: "Anna", last: "Pratt"},
   {first: "Carlie", last: "Pratt"},
   {first: "Nick", last: "Cage"},
   {first: "Aiden", last: "Att"}
  ];

 //printing the personArray
 console.log("Original list: " , personArray);
function compareFirstName(a, b){
  if (a.first < b.first){
    //checking letter value in ascii, if first to be compared is less, it is earlier in the alphabet
    //returning true keeps it in place
    return true;
  }
  else if (a.first > b.first){
    return false;
  }

  else if(a.first == b.first){
    if (a.last > b.last){
      return true;
    }
    else if(a.last > b.last){
      return false;
    }
    else if(a.last == b.last){
      return true;
    }
  }
}
function compareLastName(a, b){
  if (a.last < b.last){
    //checking letter value in ascii, if first to be compared is less, it is earlier in the alphabet
    //returning true keeps it in place
    return true;
  }
  else if (a.last > b.last){
    return false;
  }

  else if(a.last == b.last){
    if (a.first < b.first) {
      return true;
    }
    else if(a.first > b.first){
        return false;
      }
      else if(a.first == b.first){
        return true;
      }
    }
}



//commented out functions and console.logs to test:::::
selectionSort(personArray, compareFirstName);
console.log("sorted by first: " , personArray);

// selectionSort(personArray, compareLastName);
// console.log("sorted by last: " , personArray);
