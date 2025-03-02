let cal:(a:number) => (b:number) => number

cal = x => y => x + y

const with10:(b:number) => number = cal(10)

console.log(with10(5))
console.log(with10(20))

type Operator = (a:number, b:number) => number
type Calculation = (a:number) => (b:number) => (ope:Operator) => number

const calc:Calculation = a => b => ope => ope(a, b)

console.log(calc(10)(20)((a, b) => a + b))
console.log(calc(10)(20)((a, b) => a - b))
console.log(calc(10)(20)((a, b) => a * b))
console.log(calc(10)(20)((a, b) => a / b))
