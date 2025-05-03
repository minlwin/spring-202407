'use client'
import { useSearchParams } from "next/navigation"
import { useEffect } from "react"

export default function ClassEdit() {

    const params = useSearchParams()

    useEffect(() => {
        console.log(params.get('id'))
    }, [params])

    return (
      <>

      </>  
    )
}