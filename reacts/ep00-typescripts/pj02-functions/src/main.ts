function createCounter(name:string) {
    let count = 0

    return () => `${name} count is ${++ count}`
}

const counter = createCounter("Counter 1")

console.log(counter())
console.log(counter())
console.log(counter())

const other = createCounter("Counter 2")
console.log(other())
