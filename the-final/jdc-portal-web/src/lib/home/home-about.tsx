import { Card, CardContent } from "@/components/ui/card";
import { Cloud, Presentation, Tag, Wrench } from "lucide-react";
import Link from "next/link";
import React from "react";

export default function HomeAbout() {
    return (
        <section className="text-white h-screen flex flex-col justify-center px-28 bg-indigo-700">
            <h1 className="text-3xl">
                <Link href={'/anonymous'}>About Us</Link>
            </h1>

            <div className="my-6 text-xl text-secondary">
                {/* Description */}
                <p className="mb-2">Welcome to JDC — a passionate team of software developers and educators based in Myanmar. We specialize in providing high-quality software development services and hands-on programming training to individuals, businesses, and organizations.</p>
                <p className="mb-2">Our mission is to empower the next generation of developers and support companies in building efficient, scalable, and modern software solutions. Whether you're looking to grow your technical skills or need a trusted partner for your development projects, we are here to help.</p>
                <p>At JDC, we combine real-world experience with up-to-date knowledge to deliver value-driven results. With a focus on modern technologies and practical learning, we strive to make technology work for everyone.</p>
            </div>

            {/* Our Services */}
            <div>
                <h1 className="text-2xl">We Provide!</h1>
                <div className="grid grid-cols-3 gap-8 mt-4">
                    <Service title="In Campus Training" icon={
                        <Presentation size={160} />
                    } />
                    <Service title="Online Training" icon={
                        <Cloud size={160} />
                    } />
                    <Service title="Softare Development" icon={
                        <Wrench size={160} />
                    } />
                </div>
            </div>
        </section>
    )
}

function Service({icon, title, description} : {icon?:React.ReactNode, title?: string, description?:string }) {
    return (
        <Card>
            <CardContent className="flex flex-col items-center justify-center">
                {icon || <Tag />}
                <div>
                    <h1 className="text-2xl">{title || 'Service'}</h1>
                </div>
                {description && 
                    <div>{description}</div>
                }
            </CardContent>
        </Card>
    )
}
