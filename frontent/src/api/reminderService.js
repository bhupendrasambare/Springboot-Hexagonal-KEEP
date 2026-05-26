import axiosInstance from "./axiosInstance";

const safeList = (response) => {
  return response?.data?.data ?? [];
};

const safeObject = (response) => {
  return response?.data?.data ?? null;
};

export const getAllReminders = async () => {
  const response = await axiosInstance.get("/reminder");
  return safeList(response);
};

export const getCompletedReminders = async () => {
  const response = await axiosInstance.get("/reminder/completed");
  return safeList(response);
};

export const getPendingReminders = async () => {
  const response = await axiosInstance.get("/reminder/pending");
  return safeList(response);
};

export const getReminderById = async (reminderId) => {
  const response = await axiosInstance.get(
    `/reminder/${reminderId}`
  );

  return safeObject(response);
};

export const createReminder = async ({
  noteId,
  title,
  reminderTime,
  description,
}) => {

  const response = await axiosInstance.post(
    "/reminder",
    {
      noteId,
      title,
      reminderTime,
      description,
    }
  );

  return safeObject(response);
};

export const updateReminder = async ({
  reminderId,
  noteId,
  title,
  reminderTime,
  description,
  completed,
}) => {

  const response = await axiosInstance.put(
    `/reminder/${reminderId}`,
    {
      noteId,
      title,
      reminderTime,
      description,
      completed,
    }
  );

  return safeObject(response);
};

export const markReminderCompleted = async (
  reminderId
) => {

  const response = await axiosInstance.patch(
    `/reminder/${reminderId}/complete`
  );

  return safeObject(response);
};

export const markReminderIncomplete = async (
  reminderId
) => {

  const response = await axiosInstance.patch(
    `/reminder/${reminderId}/incomplete`
  );

  return safeObject(response);
};


export const deleteReminder = async (
  reminderId
) => {

  const response = await axiosInstance.delete(
    `/reminder/${reminderId}`
  );

  return safeObject(response);
};