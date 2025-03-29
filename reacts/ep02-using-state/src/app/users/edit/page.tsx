'use client'
import PageTitle from "@/components/page-title";
import { useAddUser, UserForm } from "@/providers/users-context";
import { Button, TextField } from "@mui/material";
import { useRouter } from "next/navigation";
import { useMemo, useState } from "react";

export default function UserEdit() {

    const [name, setName] = useState<string>('')
    const [phone, setPhone] = useState<string>('')
    const [email, setEmail] = useState<string>('')
    const disabledBtn = useMemo(() => name === '' || phone === '' || email === '', [name, phone, email])

    const addUser = useAddUser()
    const router = useRouter()

    const save = () => {
        const form:UserForm = {
            name: name,
            phone: phone,
            email: email
        } 

        addUser(form)

        router.push("/users")
    }

    return (
        <>
            <PageTitle title="Add New User" />

            <form className="flex flex-col gap-y-4">
                <TextField variant="standard" onChange={(e) => setName(e.target.value)} label="User Name" className="w-1/3" />
                <TextField variant="standard" onChange={(e) => setPhone(e.target.value)} label="Phone" className="w-1/3" />
                <TextField variant="standard" onChange={(e) => setEmail(e.target.value)} label="Email" className="w-1/3" />
                <div>
                    <Button onClick={save} disabled={disabledBtn} variant="contained">Save</Button>
                </div>
            </form>
        </>
    )
}