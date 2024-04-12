import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import QuestionList from "./pages/questions/QuestionList";
import "bootstrap/dist/css/bootstrap.css";

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <QuestionList />
    </QueryClientProvider>
  );
}

export default App;
