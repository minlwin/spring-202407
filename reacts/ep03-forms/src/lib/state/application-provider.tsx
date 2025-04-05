'use client'

import React from "react"
import { SideBarStatePrvider } from "./sidebar-state"

export default function ApplicationProvider({children} : {children : React.ReactNode}) {
    return (
        <SideBarStatePrvider>
            {children}
        </SideBarStatePrvider>
    )
}