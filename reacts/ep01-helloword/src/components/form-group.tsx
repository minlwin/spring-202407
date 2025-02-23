import { Label } from "flowbite-react";
import React from "react";

export default function FormGroup(
    {label, children} : 
    {
        label: string,
        children: React.ReactNode
    }) {
    return (
        <div>
            <Label>{label}</Label>
            <div>
                {children}
            </div>
        </div>
    )
}