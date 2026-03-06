import axios from "axios";
import { BASE_URL } from "../components/Constants";

const axiosInstance = axios.create({
  baseURL: BASE_URL + "/api/v1",
  headers: {
    "Content-Type": "application/json",
  },
});

axiosInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem("accessToken");

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

axiosInstance.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const refreshToken = localStorage.getItem("refreshToken");

        const response = await axios.post(
          `${BASE_URL}/api/v1/auth/refresh`,
          {
            refreshToken: refreshToken,
          }
        );

        const newAccessToken = response.data.token.accessToken;
        const newRefreshToken = response.data.token.refreshToken;

        localStorage.setItem("accessToken", newAccessToken);
        localStorage.setItem("refreshToken", newRefreshToken);

        originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;

        return axiosInstance(originalRequest);

      } catch (refreshError) {
        window.dispatchEvent(new Event("logout"));
      }
    }

    return Promise.reject(error);
  }
);

export default axiosInstance;