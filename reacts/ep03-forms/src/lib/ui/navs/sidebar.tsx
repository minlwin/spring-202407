'use client'
import { useSelectedMenuSetter } from "@/lib/state/selected-menu-provider";
import { useSideBarStateSetter } from "@/lib/state/sidebar-state";
import { Book, CalendarMonth, PeopleAlt } from "@mui/icons-material";
import { useRouter } from "next/navigation";
import React from "react";

export default function SideBar() {
    return (
        <nav>
            <SideBarHeader />

            <div>
                {MANAGEMENT_MENU.map((menu, index) => 
                    <SideBarMenuItem key={`mgm-${index}`} menu={menu} />
                )}
            </div>
        </nav>
    )
}

function SideBarMenuItem({menu} : {menu: MenuModel}) {
    const router = useRouter()
    const setDrawerState = useSideBarStateSetter()
    const setSelectedMenu = useSelectedMenuSetter()

    const navigate = () => {
        router.push(menu.route)
        setSelectedMenu(menu)
        setDrawerState(false)
    }
    return (
        <div onClick={navigate} className="flex items-center px-4 py-2 hover:bg-gray-100 cursor-pointer">
            <div className="me-2">{menu.icon}</div>
            <div className="text-lg">{menu.title}</div>
        </div>
    )
}

function SideBarHeader() {
    return (
        <section className="mt-8 mb-4 mx-16">
            <img src="/photo/jdc-logo.png" alt="JDC LOGO" />
        </section>
    )
}

const MANAGEMENT_MENU:MenuModel[] = [
    {
        title : "Course Management",
        route : "/management/courses",
        icon : <Book />
    },
    {
        title : "Class Management",
        route : "/management/classes",
        icon : <CalendarMonth />
    },
    {
        title : "Student Management",
        route : "/management/students",
        icon : <PeopleAlt />
    },
]

export type MenuModel = {
    icon: React.ReactNode,
    title: string,
    route: string
}