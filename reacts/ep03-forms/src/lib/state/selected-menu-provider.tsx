'use client'

import React, { createContext, useContext, useState } from "react"
import { MenuModel } from "../ui/navs/sidebar"
import { Home } from "@mui/icons-material"

type SelectedMenuContextType = {
    menu: MenuModel,
    setMenu:(menu:MenuModel) => void
}

const SelectedMenuContext = createContext<SelectedMenuContextType | undefined>(undefined)

const HOME:MenuModel = {
    icon: <Home />,
    title: "Home",
    route: "/"
}

function SelectedMenuProvider({children} : {children : React.ReactNode}) {
    const [menu, setMenu] = useState<MenuModel>(HOME)

    return (
        <SelectedMenuContext.Provider value={{menu: menu, setMenu: setMenu}}>
            {children}
        </SelectedMenuContext.Provider>
    )
}

function useSelectedMenuContext() {
    const context = useContext(SelectedMenuContext)

    if(context === undefined) {
        throw new Error("Invalid usage of Context")
    }
    return context
}

function useSelectedMenu() {
    return useSelectedMenuContext().menu
}

function useSelectedMenuSetter() {
    return useSelectedMenuContext().setMenu
}

export {
    SelectedMenuProvider,
    useSelectedMenu,
    useSelectedMenuSetter
}