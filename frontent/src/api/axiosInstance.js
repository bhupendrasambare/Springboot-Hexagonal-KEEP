import axios from "axios";
import { BASE_URL } from "../components/Constants";

const axiosInstance = axios.create({
  baseURL: BASE_URL+"/api/v1",
  headers: {
    "Content-Type": "application/json",
  },
});

export default axiosInstance;