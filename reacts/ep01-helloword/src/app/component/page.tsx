'use client'

import PageTitle from "@/components/page-title"
import { searchRegions } from "@/model/client/region-client"
import { Region } from "@/model/dto/region"
import { Card } from "flowbite-react"
import { useEffect, useState } from "react"

export default function Page() {

    const [regions, setRegions] = useState<Region[]>([])

    useEffect(() => {
        async function loadData() {
            const result = await searchRegions()
            setRegions(result)
        }

        loadData()
    }, [])

    return (
        <>
            <PageTitle value="Learning Component" />

            <div className="grid grid-cols-4 gap-4 mt-4">
                {regions.map((region, index) => <RegionComponent key={index} region={region} />)}
            </div>
        </>
    )
}

function RegionComponent({region} : {region:Region}) {
    return (
        <Card>
            <h5>{`${region.id} ${region.name}`}</h5>

            <p>{region.burmeseName}</p>
        </Card>
    )
}