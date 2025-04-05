'use client'

import React from "react"
import { SideBarStatePrvider } from "./sidebar-state"
import { SelectedMenuProvider } from "./selected-menu-provider"

export default function ApplicationProvider({children} : {children : React.ReactNode}) {
    return (
        <SideBarStatePrvider>
            <SelectedMenuProvider>
            {children}
            </SelectedMenuProvider>
        </SideBarStatePrvider>
    )
}