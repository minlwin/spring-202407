export default function PageTitle({title} : {title:string}) {
    return (
        <div className="mb-3">
            <h1 className="text-2xl font-black text-purple-500">{title}</h1>
        </div>
    )
}