package da.tasks.servlet.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Results
{

    private Map<String, Object> answers;

    public Results()
    {
        answers = new LinkedHashMap<>();
    }

    public synchronized void addAnswer(Question q, Boolean answer, Integer id)
    {
        if (q == null || answer == null || id == null)
        {
            return;
        }
        String key = String.valueOf(id) + "_" + q;
        answers.put(key, answer);
    }

    public synchronized List<Integer> getYesAnswers(Question q)
    {
        List<Integer> result = new ArrayList<>();
        for (String s : answers.keySet())
        {
            Integer id = Integer.valueOf(s.split("_")[0]);
            String question = s.split("_")[1];
            if ((Boolean) answers.get(s) && question.equals(q.toString()))
            {
                result.add(id);
            }
        }
        return result;
    }

    public synchronized List<Integer> getNoAnswers(Question q)
    {
        List<Integer> result = new ArrayList<>();
        for (String s : answers.keySet())
        {
            Integer id = Integer.valueOf(s.split("_")[0]);
            String question = s.split("_")[1];
            if (!(Boolean) answers.get(s) && question.equals(q.toString()))
            {
                result.add(id);
            }
        }
        return result;
    }

    public synchronized void clear()
    {
        answers = new HashMap<>();
    }
}
