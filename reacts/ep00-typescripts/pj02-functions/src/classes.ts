class Person {
    constructor(
        private readonly _firstName:string, 
        private readonly _lastName:string) {}
    
    get name() {
        return `${this._firstName} ${this._lastName}`
    }

}

const person = new Person("Thidar", "Aung")
