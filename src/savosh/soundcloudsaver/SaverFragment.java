package savosh.soundcloudsaver;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import savosh.soundcloudsaver.adapter.UniversalItemsArrayAdapter;
import savosh.soundcloudsaver.listener.OnPlayerAddItemClickListener;
import savosh.soundcloudsaver.model.Track;
import savosh.soundcloudsaver.service.TrackService;
import savosh.soundcloudsaver.task.SaveTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaverFragment extends Fragment {
    public static final String TAG = SaverFragment.class.getName();

    final static List<Track> savedTracks = TrackService.readSaved();
    final static Map<Track, SaveTask> savingsTrack = new HashMap<>();
    UniversalItemsArrayAdapter universalItemsArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(getClass().getName(), "onCreateView");
        View v = inflater.inflate(R.layout.main_saver_fragment, container, false);

        if(!ApplicationContext.getSelf().isLinkToTrackForSaveNull()) {
            Track track = ApplicationContext.getSelf().getTrackForSave();
            ApplicationContext.getSelf().removeLinkToTrackForSave();
            if (savedTracks.contains(track) || savingsTrack.containsKey(track)) {
                Log.d(getClass().getName(), "Track has already been saved");
            } else {
                savingsTrack.put(track, new SaveTask(track, savedTracks, savingsTrack));
            }
        }

        universalItemsArrayAdapter = new UniversalItemsArrayAdapter(getActivity(), UniversalItemsArrayAdapter.MODE_FOR_SAVE_RESULT, savingsTrack);
        universalItemsArrayAdapter.addAll(savingsTrack.keySet());
        universalItemsArrayAdapter.addAll(savedTracks);


        ((ListView) v.findViewById(R.id.main_saver_fragment_list_view))
                .setAdapter(universalItemsArrayAdapter);


        ((ListView) v.findViewById(R.id.main_saver_fragment_list_view))
                .setOnItemClickListener(new OnPlayerAddItemClickListener());

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(getClass().getName(), "onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(getClass().getName(), "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(getClass().getName(), "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(getClass().getName(), "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(getClass().getName(), "onResume");
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(getClass().getName(), "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        Log.d(getClass().getName(), "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(getClass().getName(), "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(getClass().getName(), "onDestroyView");
        TrackService.save(savedTracks);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(getClass().getName(), "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(getClass().getName(), "onDetach");
        super.onDetach();
    }
}
