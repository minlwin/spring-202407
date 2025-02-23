import { Region } from "../dto/region";
import axios from 'axios';

export async function searchRegions():Promise<Region[]> {
    const response = await axios.get<Region[]>('http://localhost:8080/regions')
    return response.data
}