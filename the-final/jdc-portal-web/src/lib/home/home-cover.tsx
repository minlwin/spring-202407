import { Button } from "@/components/ui/button";
import { LogIn } from "lucide-react";

export default function HomeCover() {
  return (
    <section className="h-screen bg-white flex items-center justify-center">

        <div className="flex items-center ">
            <img src="/logo/logo2017.png" width={200} />

            <div className="ml-8">
                <h1 className="text-4xl mb-4 text-indigo-600">WELCOME TO</h1>
                <h1 className="text-6xl text-red-600 font-extrabold">JAVA</h1>
                <h1 className="text-6xl text-indigo-800 font-bold">DEVELOPER CLASS</h1>

                <div className="mt-4">
                    <Button variant={'outline'}>
                        <LogIn /> Sign In
                    </Button>
                </div>
            </div>
        </div>
    </section>
  )  
}