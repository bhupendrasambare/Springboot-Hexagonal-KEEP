import { useState, useEffect } from "react";
import { useAuth } from "../store/AuthContext";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../api/axiosInstance";

function Login() {
  const { login, logout } = useAuth();
  const navigate = useNavigate();

  const [checkingAuth, setCheckingAuth] = useState(true);

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    checkExistingSession();
  }, []);

  const checkExistingSession = async () => {
    const refreshToken = localStorage.getItem("refreshToken");

    if (!refreshToken) {
      setCheckingAuth(false);
      return;
    }

    try {
      const response = await axiosInstance.post(
        "/auth/refresh",
        {
          refreshToken,
        }
      );

      const authData = response.data.data;

      login(authData);

      navigate("/home", { replace: true });

    } catch (error) {

      console.error(
        "Refresh token validation failed",
        error
      );

      logout();

      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");

      setCheckingAuth(false);
    }
  };

  const handleLogin = async (e) => {

    e.preventDefault();

    setError("");
    setLoading(true);

    try {

      const response = await axiosInstance.post(
        "/auth/login",
        {
          email,
          password,
        }
      );

      const authData = response.data.data;

      login(authData);

      navigate("/home");

    } catch (err) {

      setError(
        err.response?.data?.message ||
        "Invalid email or password"
      );

    } finally {

      setLoading(false);

    }
  };

  const handleGoogleLogin = () => {
    window.location.href =
      "http://localhost:8080/oauth2/authorization/google";
  };

  if (checkingAuth) {
    return (
      <div className="vh-100 d-flex justify-content-center align-items-center">
        <div className="spinner-border" />
      </div>
    );
  }

  return (
    
<section
      className="vh-100 d-flex align-items-center"
    >
      <div className="container">
        <div className="row justify-content-center align-items-center py-5 h-80 w-80 shadow-lg border-0 rounded-4">

            {/* Left Illustration */}
            <div className="col-md-6 d-none d-md-block text-center">
              <img
                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
                className="img-fluid"
                alt="Login"
              />
            </div>

            {/* Login Form */}
            <div className="col-md-6 col-md-5">
              <div className=" p-4">

                <h2 className="fw-bold mb-2 text-center">Welcome to Keep Notes</h2>
                <p className="text-muted text-center mb-4">
                  Sign in to continue managing your notes.
                </p>

                <form onSubmit={handleLogin}>

                  {/* Email */}
                  <div className="mb-3">
                    <label className="form-label fw-semibold">
                      Email Address
                    </label>
                    <input
                      type="email"
                      className="form-control form-control-md"
                      placeholder="Enter your email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      required
                    />
                  </div>

                  {/* Password */}
                  <div className="mb-3">
                    <label className="form-label fw-semibold">
                      Password
                    </label>
                    <input
                      type="password"
                      className="form-control form-control-md"
                      placeholder="Enter your password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                    />
                  </div>

                  {/* Error */}
                  {error && (
                    <div className="alert alert-danger py-2">
                      {error}
                    </div>
                  )}

                  {/* Submit */}
                  <button
                    type="submit"
                    className="btn btn-primary btn-sm w-100 mt-2"
                    disabled={loading}
                  >
                    {loading ? "Signing in..." : "Sign In"}
                  </button>

                  {/* Divider */}
                  <div className="text-center my-3 text-muted">
                    — OR —
                  </div>

                  {/* Google Login */}
                  <button
                    type="button"
                    onClick={handleGoogleLogin}
                    className="btn btn-light border w-100 d-flex align-items-center justify-content-center gap-2"
                  >
                    Login with Google
                  </button>

                </form>

              </div>
            </div>


        </div>
      </div>
    </section>
  );
}

export default Login;
