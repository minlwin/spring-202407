function printAfter3Second(value:number) {
    setTimeout(() => {
        console.log(value)
    }, 3000);
}

function asyncLoop() {
    for(var i = 1; i <=3 ; i ++) {
        printAfter3Second(i)
    }
}

asyncLoop()