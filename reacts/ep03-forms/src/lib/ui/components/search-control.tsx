import { Refresh, FilterAltOutlined, Add } from "@mui/icons-material";
import { Button, ButtonGroup } from "@mui/joy";

export default function SearchControl({onRefresh, onFilter, onAdd} : {
    onRefresh?: VoidFunction,
    onFilter?: VoidFunction,
    onAdd?: VoidFunction 
}) {
    return (
        <ButtonGroup>
            <Button onClick={onRefresh} startDecorator={<Refresh/>}>Reload</Button>
            <Button onClick={onFilter} startDecorator={<FilterAltOutlined />}>Filter</Button>
            <Button onClick={onAdd} startDecorator={<Add />}>Create</Button>
        </ButtonGroup>
    )
}