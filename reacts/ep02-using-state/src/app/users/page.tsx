'use client'
import PageTitle from "@/components/page-title";
import { useUsers } from "@/providers/users-context";
import { Button, Table, TableBody, TableCell, TableHead, TableRow } from "@mui/material";
import { useRouter } from "next/navigation";

export default function UserList() {

    const users = useUsers()
    const router = useRouter()

    return (
        <>
            <div className="flex justify-between">
                <PageTitle title="User List" /> 
                <Button variant="contained" onClick={() => router.push('users/edit')}>Add User</Button>
            </div>

            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Name</TableCell>
                        <TableCell>Phone</TableCell>
                        <TableCell>Email</TableCell>
                    </TableRow>
                </TableHead>

                <TableBody>
                    {users.map(user => 
                        <TableRow key={user.id}>
                            <TableCell>{user.id}</TableCell>
                            <TableCell>{user.name}</TableCell>
                            <TableCell>{user.phone}</TableCell>
                            <TableCell>{user.email}</TableCell>
                        </TableRow>
                    )}
                </TableBody>
            </Table>
        </>
    )
}