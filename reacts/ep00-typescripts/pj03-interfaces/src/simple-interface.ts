interface CameraInf {
    takePicture():void
}

interface CameraConstructorInf {
    new (lens:[number, number]):CameraInf
}

const cameraConstructor:CameraConstructorInf = class Camera implements CameraInf {
    
    constructor(private lens: [number, number]) {}
    
    takePicture(): void {
        console.log(`Taking picture with lens ${this.lens[0]} to ${this.lens[1]}`)
    }
}

function createCamera(builder:CameraConstructorInf, lens: [number, number]) {
    return new builder(lens);
}

let camera = createCamera(cameraConstructor, [20, 100])

camera.takePicture()