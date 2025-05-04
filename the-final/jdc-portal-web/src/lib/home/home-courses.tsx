import { Card, CardContent, CardHeader } from "@/components/ui/card"
import { Coffee, Laptop, Leaf, Trophy } from "lucide-react"

export default function OurCourses() {
    return (
        <section className="h-screen flex flex-col justify-center items-center bg-red-200 px-36">

            <h1 className="text-4xl">Courses for Training Program</h1>
            <div className="mt-2 mb-8 text-xl">
                At JDC, we offer practical, hands-on programming courses designed to help students build real-world skills and become job-ready developers. Our current courses include:
            </div>
            <div className="grid grid-cols-2 gap-4">
                <Course icon={<Coffee />}
                    title="Java Basic" 
                    description="Perfect for beginners, this course introduces the fundamentals of Java programming. Students will learn core concepts such as variables, loops, object-oriented programming, and more. No prior experience is required — just a willingness to learn."/>
                <Course icon={<Leaf />}
                    title="Spring Framework" 
                    description="This course is ideal for those who have a basic understanding of Java and want to build powerful, enterprise-level applications. You'll learn about Spring Boot, RESTful APIs, Dependency Injection, and how to create scalable backend systems." />
                <Course icon={<Laptop />}
                    title="React" 
                    description="Aimed at front-end enthusiasts, our React course teaches how to build dynamic web applications using one of the most popular JavaScript libraries. Topics include components, hooks, state management, and integration with APIs."/>

                <Course icon={<Trophy />}
                    title="One Stop Bootcamp" 
                    description="Our One-Stop Full Stack Bootcamp is a complete training program designed for those who want to become full-stack developers. This all-in-one course combines Java Basics, Spring Framework, and React into a single, streamlined learning path."/>
            </div>

            <div className="mt-8">
                <p className="text-xl">All our courses are taught by experienced instructors with real industry experience, and we focus on project-based learning to help you apply your skills in real scenarios.</p>
            </div>
        </section>
    )
}

function Course({title, description, icon} : {title: string, description: string, icon : React.ReactNode}) {
    return (
        <Card className="w-full">
            <CardHeader>
                <h1 className="text-xl font-bold flex items-center">{icon} <div className="me-2" /> {title}</h1>
            </CardHeader>

            <CardContent>
                <p>{description}</p>
            </CardContent>
        </Card>
    )
}