import axiosInstance from "./axiosInstance";

/* ================= FETCH NOTES ================= */

export const getPinnedNotesApi = async () => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: true,
    archived: false,
    trashed: false,
    keyword: "",
    page: 0,
    pageSize: 10,
  });

  return response.data.data;
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

  return response.data.data;
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

  return response.data.data;
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

  return response.data.data;
};


export const createNoteApi = async (note) => {
  const response = await axiosInstance.post("/notes", note);
  return response.data.data;
};


export const pinNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/pin/${noteId}`);
  return response.data;
};

export const unPinNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/un-pin/${noteId}`);
  return response.data;
};


export const archiveNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/archive/${noteId}`);
  return response.data;
};

export const unArchiveNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/un-archive/${noteId}`);
  return response.data;
};


export const trashNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/trash/${noteId}`);
  return response.data;
};

export const unTrashNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/un-trash/${noteId}`);
  return response.data;
};