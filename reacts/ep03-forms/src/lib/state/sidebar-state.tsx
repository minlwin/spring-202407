'use client'

import React, { createContext, useContext, useState } from "react"

type SideBarState = {
    open:boolean 
    setOpen:(open:boolean) => void
}

const SideBarStateContext = createContext<SideBarState | undefined>(undefined)

function SideBarStatePrvider({children}: {children: React.ReactNode}) {
    const [open, setOpen] = useState<boolean>(false)
    return (
        <SideBarStateContext.Provider value={{open: open, setOpen: setOpen}}>
            {children}
        </SideBarStateContext.Provider>
    )
}

function useSideBarStateContext() {
    const context = useContext(SideBarStateContext)
    if(context === undefined) {
        throw Error('Invalid context usage of Side Bar State')
    }
    return context
}

function useSideBarState() {
    return useSideBarStateContext().open
}

function useSideBarStateSetter() {
    return useSideBarStateContext().setOpen
}

export {
    SideBarStatePrvider,
    useSideBarState,
    useSideBarStateSetter
}