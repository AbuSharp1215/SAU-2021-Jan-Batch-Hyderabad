//Sheik Abudhahir K
const ar = [13,5,23,4,7,6];

//user defined function
function cReduce(arr,fun){
    let val, acc=0;
    for(let i=0;i<arr.length-1;i++){
        acc+=arr[i];
        val = fun(acc,arr[i+1]);
    }
    return val;
}
// Call user defined function
let as = cReduce(ar,(acc,val) =>{
    console.log(acc);
    console.log(val);
    return acc+val;
});
console.log(as);

// call pre built function
let a = ar.reduce((acc,val) =>{
    console.log(acc)
    console.log(val)
    return acc+val;
})
console.log(a);
