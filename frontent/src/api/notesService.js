import axiosInstance from "./axiosInstance";

export const getNotesApi = async () => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: true,
    archived: true,
    trashed: true,
    keyword: "",
    page: 0,
    pageSize: 10,
  });

  return response.data.content || response.data;
};

export const createNoteApi = async (note) => {
  const response = await axiosInstance.post("/notes", note);
  return response.data;
};