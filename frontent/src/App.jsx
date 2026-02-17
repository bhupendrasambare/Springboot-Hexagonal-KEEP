import { BrowserRouter } from "react-router-dom";
import { AuthProvider } from "./store/AuthContext";
import { ThemeProvider } from "./store/ThemeContext";
import AppRoutes from "./routes/AppRoutes";

function App() {
  return (
    <BrowserRouter>
      <ThemeProvider>
        <AuthProvider>
          <AppRoutes />
        </AuthProvider>
      </ThemeProvider>
    </BrowserRouter>
  );
}

export default App;