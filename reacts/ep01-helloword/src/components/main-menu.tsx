import Link from "next/link";

export default function MainMenu() {
    return (
        <>
            <nav className="bg-slate-200 shadow px-8 py-4 flex justify-between">
                <h5>Hello React</h5>

                <ul className="flex">
                    <li className="px-2">
                        <Link href={"/component"}>Component</Link>
                    </li>
                    <li className="px-2">
                        <Link href={"/counter"}>Counter</Link>
                    </li>
                    <li className="px-2">
                        <Link href={"/form"}>Form</Link>
                    </li>
                </ul>
            </nav>
        </>
    )
}