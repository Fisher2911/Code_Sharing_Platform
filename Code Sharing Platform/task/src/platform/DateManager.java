package platform;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import platform.repositories.DatedCodeRepository;

import java.util.*;

@Component
public class DateManager {

    @Autowired
    private DatedCodeRepository datedCodeRepository;

    private final List<DatedCode> codeList = new ArrayList<>();
    private final List<DatedCode> nonRestrictedList = new ArrayList<>();
    private final Map<UUID, DatedCode> datedCodeMap = new HashMap<>();
    private int latest;

    public DateManager() {
    }

    /**
     *
     * @param datedCode
     * @return id
     */
    public void addDatedCode(final DatedCode datedCode) {
        codeList.add(datedCode);
        datedCode.setId(latest);
        datedCodeMap.put(datedCode.getUuid(), datedCode);
        if (!datedCode.isRestricted()) {
            nonRestrictedList.add(datedCode);
        } else {
            System.out.println("Dated Code is restricted: " + datedCode);
        }
        latest++;
    }

    public void saveDatedCode(final DatedCode datedCode) {
        datedCodeRepository.save(datedCode);
    }

//    public void updateDatedCode(final DatedCode datedCode) {
//        datedCodeRepository.
//    }

    public void deleteDatedCode(final DatedCode datedCode) {
        nonRestrictedList.remove(datedCode);
        codeList.remove(datedCode);
        datedCodeMap.remove(datedCode.getUuid());
        datedCodeRepository.delete(datedCode);
        System.out.println("Deleted DatedCode: " + datedCode);
    }

    public int totalCodes() {
        return codeList.size();
    }

    public DatedCode getDatedCodeFromId(final int id) {
        return codeList.get(id);
    }

    public DatedCode getDatedCodeFromUUID(final UUID uuid) {
        return datedCodeMap.get(uuid);
    }

    public Map<UUID, DatedCode> getDatedCodeMap() {
        return datedCodeMap;
    }

    public List<DatedCode> getDatedCode(final int id, final int amountBefore) {
        final List<DatedCode> datedCodes = new ArrayList<>();
        int current = id;
        int total = 0;
        while (total < amountBefore && current >= 0) {
            if (current >= codeList.size()) {
                current--;
                continue;
            }
            final DatedCode datedCode = codeList.get(current);
            if (datedCode == null || datedCode.isRestricted()) {
                current--;
                continue;
            }
            datedCodes.add(datedCode);
            total++;
            current--;
        }
        return datedCodes;
    }

    public int getLatest() {
        return latest;
    }

    public DatedCode getLatestAdded() {
        return codeList.get(getLatest());
    }
}
