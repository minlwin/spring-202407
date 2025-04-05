import { Typography } from "@mui/joy";
import React from "react";

export default function SideBar() {
    return (
        <nav>
            <Typography level="h3">Side Menu</Typography>
        </nav>
    )
}

function SideBarHeader() {
    return (
        <section>

        </section>
    )
}

const MANAGEMENT_MENU:MenuModel[] = [
    
]

type MenuModel = {
    icon: React.ReactNode,
    title: string,
    route: string
}