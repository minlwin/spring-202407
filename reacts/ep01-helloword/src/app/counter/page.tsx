'use client'

import PageTitle from "@/components/page-title";
import { Button, Card } from "flowbite-react";
import { useState } from "react";

export default function Counter() {

    const [count, setCount] = useState(0)

    const countDown = () => setCount(count - 1)
    const countUp = () => setCount(count + 1)

    return (
        <>
            <PageTitle value="Learning Use State" />

            <Card className="w-1/3 mt-4">

                <div className="grid grid-cols-3">
                    <Button onClick={countDown}>Minus One</Button>
                    <div className="text-3xl text-center">{count}</div>
                    <Button onClick={countUp}>Plus One</Button>
                </div>
            </Card>
        </>
    )
}