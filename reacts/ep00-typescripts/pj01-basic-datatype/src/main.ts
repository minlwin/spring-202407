const greet = (name:string) => `Hello ${name}`

console.log(greet("JDC"))

/**
 * Learning Turple Type
 */
type MyTurple = [number, string]

function test(value:MyTurple):MyTurple {
    const result = []
    result[0] = value[0] * 2
    result[1] = `Converted ${value[1]}`
    return result as MyTurple
}

const result = test([1, "Hello"])
console.log(`result[0] : ${result[0]}`)
console.log(`result[1] : ${result[1]}`)

/**
 * Any Vs Unknown
 */

function useAny(value:any) {
    console.log(value.length)
}

function useUnknown(value:unknown) {

}

useAny([0,1])
useAny(10)

useUnknown([0,1])

/**
 * Using Literals, Union and Interception Types
 */

type Zero = 0
type One = 1;

type ZeroOrOne = Zero | One

let zero:ZeroOrOne = 1

type Status = "Pending" | "Success" | "Cancel" | "Error"

type Person = {
    id: number,
    name: string,
    phone: string,
    email: string,
}

type Contact = {
    email: string,
    address: string,
    township: string
}

type PersonWithContact = Person & Contact

let aungAung:PersonWithContact = {
    id: 0,
    name: "Aung Aung",
    phone: "0918171711",
    email: "aung@gmail.com",
    address: "Kamayut 1 Quarter",
    township: "Kamayut"
}

/**
 * Using Enum
 */

enum Result {
    Success, Fails
}

console.log(Result)