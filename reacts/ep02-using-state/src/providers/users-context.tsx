'use client'
import React, { createContext, useContext, useState } from "react"

export interface UserForm {
    name: string
    phone: string
    email: string
}

export type User = {
    id: number
} & UserForm

type UsersContextType = {
    users: User[]
    addUser: (form:UserForm) => void
}

const UsersConext = createContext<UsersContextType | undefined>(undefined)

export function UsersProvider({children} : {children:React.ReactNode}) {
    const [users, setUsers] = useState<User[]>([])
    const [id, setId] = useState(0)

    const addUser = (form:UserForm) => {
        setId(id + 1)
        const user:User = {id: id, ...form}
        setUsers([...users, user])
    }
    return (
        <UsersConext.Provider value={{users, addUser}}>
            {children}
        </UsersConext.Provider>
    )
}

function useUsersContext() {
    const context = useContext(UsersConext)

    if(context === undefined) {
        throw Error("Invalid usage of context.")
    }

    return context
}

export function useUsers() {
    return useUsersContext().users
}

export function useAddUser() {
    return useUsersContext().addUser
}