import { Book, Calendar, Home, LogIn, Wrench } from "lucide-react";
import Link from "next/link";
import React from "react";

export default function Layout({children} : {children:React.ReactNode}) {
    return (
        <main className="h-screen flex flex-col">
            <NavBar />

            <div className="h-full px-12 py-4">
                {children}
            </div>
        </main>
    )
}

function NavBar() {
    return (
        <nav className="shadow px-12 flex justify-between items-center h-16">
            <Link className="text-xl flex items-center" href={'/'}>
                <Home className="me-2" /> JDC Portal
            </Link>

            <div className="flex">
                <AppMenuItem name="Developing" href="/anonymous/signin">
                    <Wrench size={18} />
                </AppMenuItem>
                <AppMenuItem name="Courses" href="/anonymous/signin">
                    <Book size={18} />
                </AppMenuItem>
                <AppMenuItem name="Resent Classes" href="/anonymous/signin">
                    <Calendar size={18} />
                </AppMenuItem>
                <AppMenuItem name="Sign In" href="/anonymous/signin">
                    <LogIn size={18} />
                </AppMenuItem>
            </div>
        </nav>
    )
}

function AppMenuItem({name, href, children} : {name:string, href:string, children:React.ReactNode}) {
    return (
        <Link href={href} className="flex items-center px-2">
            <span className="me-2">{children}</span> {name}
        </Link>
    )
}