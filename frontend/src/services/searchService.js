import AwesomeDebouncePromise from "awesome-debounce-promise";
import { useEffect, useState } from "react";
import { useAsyncAbortable } from "react-async-hook";
import useConstant from "use-constant";
import { searchCricInfo } from "../server";

const useSearchService = () => {
  const [inputText, setInputText] = useState("");

  useEffect(() => {
    console.log(inputText);
  }, [inputText]);

  // Debounce the original search async function
  const debouncedSearch = useConstant(() =>
    AwesomeDebouncePromise(searchCricInfo, 1000)
  );

  const search = useAsyncAbortable(
    async (abortSignal, inputText) => {
      // If the input is empty, return nothing immediately (without the debouncing delay!)

      if (inputText.length === 0) {
        return [];
      }
      // Else we use the debounced api
      else {
        return debouncedSearch(inputText, abortSignal);
      }
    },
    // Ensure a new request is made everytime the text changes (even if it's debounced)
    [inputText]
  );

  // Return everything needed for the hook consumer
  return {
    inputText,
    setInputText,
    search,
  };
};

export default useSearchService;
