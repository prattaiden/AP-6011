
// Function to find the index of the minimum element in the array
function findMinLocation(arr, startIndex, compareFunction) {
  let minIndex = startIndex;
  for (let i = startIndex + 1; i < arr.length; i++) {
    if (compareFunction(arr[i], arr[minIndex])) {
      minIndex = i;
    }
  }
  return minIndex;
}

// Selection Sort Function
function selectionSort(arr, compareFunction) {
  for (let i = 0; i < arr.length; i++) {
    const minIndex = findMinLocation(arr, i, compareFunction);
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
function compareString(a, b) {
  return a.length < b.length;
}

// Test the sorting function with different types of data
const intArray = [55, 33, 71, 100, 9, 2000];
selectionSort(intArray, compareInt);
console.log( intArray);

const strArray = ["short", "cervelo", "me", "recognized"];
selectionSort(strArray, compareString);
console.log( strArray);
