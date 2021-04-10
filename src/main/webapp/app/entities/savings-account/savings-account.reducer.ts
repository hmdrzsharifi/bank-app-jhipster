import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISavingsAccount, defaultValue } from 'app/shared/model/savings-account.model';

export const ACTION_TYPES = {
  FETCH_SAVINGSACCOUNT_LIST: 'savingsAccount/FETCH_SAVINGSACCOUNT_LIST',
  FETCH_SAVINGSACCOUNT: 'savingsAccount/FETCH_SAVINGSACCOUNT',
  CREATE_SAVINGSACCOUNT: 'savingsAccount/CREATE_SAVINGSACCOUNT',
  UPDATE_SAVINGSACCOUNT: 'savingsAccount/UPDATE_SAVINGSACCOUNT',
  DELETE_SAVINGSACCOUNT: 'savingsAccount/DELETE_SAVINGSACCOUNT',
  RESET: 'savingsAccount/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISavingsAccount>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type SavingsAccountState = Readonly<typeof initialState>;

// Reducer

export default (state: SavingsAccountState = initialState, action): SavingsAccountState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SAVINGSACCOUNT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SAVINGSACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SAVINGSACCOUNT):
    case REQUEST(ACTION_TYPES.UPDATE_SAVINGSACCOUNT):
    case REQUEST(ACTION_TYPES.DELETE_SAVINGSACCOUNT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SAVINGSACCOUNT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SAVINGSACCOUNT):
    case FAILURE(ACTION_TYPES.CREATE_SAVINGSACCOUNT):
    case FAILURE(ACTION_TYPES.UPDATE_SAVINGSACCOUNT):
    case FAILURE(ACTION_TYPES.DELETE_SAVINGSACCOUNT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SAVINGSACCOUNT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SAVINGSACCOUNT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SAVINGSACCOUNT):
    case SUCCESS(ACTION_TYPES.UPDATE_SAVINGSACCOUNT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SAVINGSACCOUNT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/savings-accounts';

// Actions

export const getEntities: ICrudGetAllAction<ISavingsAccount> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SAVINGSACCOUNT_LIST,
  payload: axios.get<ISavingsAccount>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ISavingsAccount> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SAVINGSACCOUNT,
    payload: axios.get<ISavingsAccount>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISavingsAccount> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SAVINGSACCOUNT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISavingsAccount> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SAVINGSACCOUNT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISavingsAccount> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SAVINGSACCOUNT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
