import axiosInstance from "./axiosInstance";

export const getPinnedNotesApi = async () => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: true,
    archived: false,
    trashed: false,
    keyword: "",
    page: 0,
    pageSize: 10,
  });

  return response.data.content || response.data;
};

export const getNotesApi = async () => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: false,
    archived: false,
    trashed: false,
    keyword: "",
    page: 0,
    pageSize: 10,
  });

  return response.data.content || response.data;
};

export const getArchiveNotesApi = async () => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: false,
    archived: true,
    trashed: false,
    keyword: "",
    page: 0,
    pageSize: 10,
  });

  return response.data.content || response.data;
};

export const getTrashNotesApi = async () => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: false,
    archived: false,
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