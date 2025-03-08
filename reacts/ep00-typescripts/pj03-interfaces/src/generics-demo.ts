function showLength<T extends LengthEnable>(value:T) {
    console.log(`Length of value is ${value.length}`)
}

interface LengthEnable {
    readonly length:number
}