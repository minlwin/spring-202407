'use client'
import PageTitle from "@/components/page-title";
import { Button, Card, List, ListItem, TextField } from "@mui/material";
import { useState } from "react";

export default function SimpleInput() {

    const [value, setValue] = useState<string>('')
    const [list, setList] = useState<string[]>([])
    
    const add = () => {
        if(value !== '') {
            setList([...list, value])
            setValue('')
        }
    }

    return (
        <>
            <PageTitle title="Simple Input" />

            <Card variant="outlined" className="p-8">
                <div className="flex items-stretch">
                    <TextField onChange={event => setValue(event.target.value)} value={value} label="Task" size="small"/>
                    <div className="px-1"></div>
                    <Button onClick={add} variant="contained" disabled={value === ''} size="medium">Add</Button>
                </div>
            </Card>

            {list.length > 0 && (
                <section className="mt-4">
                    <h1 className="text-lg mb-4">Task List</h1>

                    <Card variant="outlined">
                        <List>
                            {list.map((item, index) => (
                                <ListItem className="hover:bg-gray-200" key={index}>{item}</ListItem>
                            ))}
                        </List>
                    </Card>
                </section>
            )}
        </>
    )
}