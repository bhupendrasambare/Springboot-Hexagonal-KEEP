import axiosInstance from "./axiosInstance";


const safeContent = (response) => {
  return response?.data?.data?.content ?? [];
};

const safePage = (response) => {
  return response?.data?.data ?? {
    content: [],
    totalElements: 0,
    totalPages: 0,
    size: 0,
    number: 0,
  };
};


export const getPinnedNotesApi = async (page = 0, size = 10) => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: true,
    archived: false,
    trashed: false,
    keyword: "",
    page,
    pageSize: size,
  });

  return safePage(response);
};

export const getNotesApi = async (page = 0, size = 10) => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: false,
    archived: false,
    trashed: false,
    keyword: "",
    page,
    pageSize: size,
  });

  return safePage(response);
};

export const getArchiveNotesApi = async (page = 0, size = 10) => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: false,
    archived: true,
    trashed: false,
    keyword: "",
    page,
    pageSize: size,
  });

  return safePage(response);
};

export const getTrashNotesApi = async (page = 0, size = 10) => {
  const response = await axiosInstance.post("/notes/find", {
    pinned: false,
    archived: false,
    trashed: true,
    keyword: "",
    page,
    pageSize: size,
  });

  return safePage(response);
};


export const createNoteApi = async (note) => {
  const response = await axiosInstance.post("/notes", note);
  return response?.data?.data ?? null;
};


export const pinNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/pin/${noteId}`);
  return response?.data ?? null;
};

export const unPinNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/un-pin/${noteId}`);
  return response?.data ?? null;
};


export const archiveNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/archive/${noteId}`);
  return response?.data ?? null;
};

export const unArchiveNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/un-archive/${noteId}`);
  return response?.data ?? null;
};


export const trashNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/trash/${noteId}`);
  return response?.data ?? null;
};

export const unTrashNoteApi = async (noteId) => {
  const response = await axiosInstance.post(`/notes/un-trash/${noteId}`);
  return response?.data ?? null;
};

export const searchNotes = async (searchString, retryCount = 0) => {
  try {
    const response = await axiosInstance.get(
      `/notes/search-notes?request=${searchString}`
    );

    return response?.data ?? null;

  } catch (error) {
    const status = error?.response?.status;

    if (status === 500 && retryCount < 2) {
      console.warn(`Retrying search... attempt ${retryCount + 1}`);

      await new Promise((resolve) => setTimeout(resolve, 10000));
      return searchNotes(searchString, retryCount + 1);
    }

    console.error("Search API failed:", error);
    throw error;
  }
};